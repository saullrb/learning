defmodule LogParser do
  def valid_line?(line) do
    ~r/^\[(DEBUG|INFO|WARNING|ERROR)\]/
    |> Regex.match?(line)
  end

  def split_line(line) do
    ~r/<[~*=-]*>/
    |> Regex.split(line)
  end

  def remove_artifacts(line) do
    ~r/end-of-line\d+/i
    |> Regex.replace(line, "")
  end

  def tag_with_user_name(line) do
    case Regex.run(~r/User\s+([^\s]+)/u, line) do
      [_, username] -> "[USER] #{username} " <> line
      nil -> line
    end
  end
end
