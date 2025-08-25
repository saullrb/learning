defmodule RPNCalculator.Exception do
  defmodule DivisionByZeroError do
    defexception message: "division by zero occurred"
  end

  defmodule StackUnderflowError do
    defexception message: "stack underflow occurred"

    def exception(value) do
      case value do
        [] -> %__MODULE__{}
        _ -> %__MODULE__{message: "stack underflow occurred, context: #{value}"}
      end
    end
  end

  def divide(numbers) when length(numbers) != 2, do: raise(StackUnderflowError, "when dividing")

  def divide([divisor | _]) when divisor == 0, do: raise(DivisionByZeroError)

  def divide([divisor, dividend]), do: dividend / divisor
end
