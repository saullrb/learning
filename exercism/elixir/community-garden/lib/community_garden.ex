# Use the Plot struct as it is provided
defmodule Plot do
  @enforce_keys [:plot_id, :registered_to]
  defstruct [:plot_id, :registered_to]
end

defmodule CommunityGarden do
  def start(opts \\ []) do
    Agent.start(fn -> %{plots: [], id_counter: 1} end, opts)
  end

  def list_registrations(pid) do
    Agent.get(pid, fn %{plots: plots} -> plots end)
  end

  def register(pid, register_to) do
    Agent.get_and_update(pid, fn %{plots: plots, id_counter: id} = state ->
      new_plot = %Plot{plot_id: id, registered_to: register_to}
      {new_plot, %{state | plots: [new_plot | plots], id_counter: id + 1}}
    end)
  end

  def release(pid, plot_id) do
    Agent.update(pid, fn state ->
      %{state | plots: Enum.reject(state.plots, &(&1.plot_id == plot_id))}
    end)
  end

  def get_registration(pid, plot_id) do
    Agent.get(pid, fn %{plots: plots} ->
      Enum.find(
        plots,
        {:not_found, "plot is unregistered"},
        fn plot ->
          plot.plot_id == plot_id
        end
      )
    end)
  end
end
