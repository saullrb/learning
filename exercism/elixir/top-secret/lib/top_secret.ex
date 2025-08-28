defmodule TopSecret do
  def to_ast(string) do
    Code.string_to_quoted!(string)
  end

  def decode_secret_message_part(ast, acc) do
    case ast do
      {kind, _, [{_, _, nil} | _]} when kind in [:def, :defp] ->
        {ast, ["" | acc]}

      {kind, _, [{:when, _, [{name, _, args} | _]} | _]} when kind in [:def, :defp] ->
        {ast, [build_part(name, args) | acc]}

      {kind, _, [{name, _, args} | _]} when kind in [:def, :defp] ->
        {ast, [build_part(name, args) | acc]}

      _ ->
        {ast, acc}
    end
  end

  defp build_part(name, args) do
    name |> to_string() |> String.slice(0, length(args))
  end

  def decode_secret_message(string) do
    {_, acc} =
      string
      |> to_ast()
      |> Macro.prewalk([], &decode_secret_message_part/2)

    acc
    |> Enum.reverse()
    |> Enum.join("")
  end
end
