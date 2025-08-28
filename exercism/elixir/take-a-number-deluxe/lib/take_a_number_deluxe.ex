defmodule TakeANumberDeluxe do
  use GenServer

  # Client API
  @spec start_link(keyword()) :: {:ok, pid()} | {:error, atom()}
  def start_link(opts) do
    GenServer.start_link(__MODULE__, opts)
  end

  @spec report_state(pid()) :: TakeANumberDeluxe.State.t()
  def report_state(machine) do
    GenServer.call(machine, :report)
  end

  @spec queue_new_number(pid()) :: {:ok, integer()} | {:error, atom()}
  def queue_new_number(machine) do
    GenServer.call(machine, :queue_number)
  end

  @spec serve_next_queued_number(pid(), integer() | nil) :: {:ok, integer()} | {:error, atom()}
  def serve_next_queued_number(machine, priority_number \\ nil) do
    GenServer.call(machine, {:serve_number, priority_number})
  end

  @spec reset_state(pid()) :: :ok
  def reset_state(machine) do
    GenServer.cast(machine, :reset)
  end

  # Server callbacks
  @impl GenServer
  def init(opts) do
    with min <- Keyword.get(opts, :min_number),
         max <- Keyword.get(opts, :max_number),
         timeout <- Keyword.get(opts, :auto_shutdown_timeout, :infinity),
         {:ok, state} <- TakeANumberDeluxe.State.new(min, max, timeout) do
      {:ok, state, timeout}
    else
      _ -> {:stop, :invalid_configuration}
    end
  end

  @impl GenServer
  def handle_call(:report, _from, state) do
    {:reply, state, state, state.auto_shutdown_timeout}
  end

  @impl GenServer
  def handle_call(:queue_number, _from, state) do
    case TakeANumberDeluxe.State.queue_new_number(state) do
      {:ok, new_number, new_state} ->
        {:reply, {:ok, new_number}, new_state, new_state.auto_shutdown_timeout}

      {:error, error} ->
        {:reply, {:error, error}, state, state.auto_shutdown_timeout}
    end
  end

  @impl GenServer
  def handle_call({:serve_number, priority_number}, _from, state) do
    case TakeANumberDeluxe.State.serve_next_queued_number(state, priority_number) do
      {:ok, next_number, new_state} ->
        {:reply, {:ok, next_number}, new_state, new_state.auto_shutdown_timeout}

      {:error, error} ->
        {:reply, {:error, error}, state, state.auto_shutdown_timeout}
    end
  end

  @impl GenServer
  def handle_cast(:reset, state) do
    new_state = %TakeANumberDeluxe.State{state | queue: TakeANumberDeluxe.Queue.new()}
    {:noreply, new_state, state.auto_shutdown_timeout}
  end

  @impl GenServer
  def handle_info(:timeout, _state) do
    exit(:normal)
  end

  @impl GenServer
  def handle_info(_msg, state) do
    {:noreply, state, state.auto_shutdown_timeout}
  end
end
