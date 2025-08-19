defmodule Username do
  def sanitize(username) do
    username
    |> Enum.flat_map(fn ch ->
      case ch do
        ?ä -> ~c"ae"
        ?ö -> ~c"oe"
        ?ü -> ~c"ue"
        ?ß -> ~c"ss"
        _ -> [ch]
      end
    end)
    |> Enum.filter(&(&1 in ?a..?z or &1 == ?_))
  end
end
