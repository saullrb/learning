defmodule DNA do
  @encode_map %{
    ?\s => 0,
    ?A => 1,
    ?C => 2,
    ?G => 4,
    ?T => 8
  }

  @decode_map Map.new(@encode_map, fn {k, v} -> {v, k} end)

  def encode_nucleotide(code_point) do
    @encode_map[code_point]
  end

  def decode_nucleotide(encoded_code) do
    @decode_map[encoded_code]
  end

  def encode(dna), do: do_encode(dna, <<>>)

  defp do_encode([], encoded_bitstring), do: encoded_bitstring

  defp do_encode([h | t], encoded_bitstring) do
    encoded = encode_nucleotide(h)
    new_bitstring = <<encoded_bitstring::bitstring, encoded::4>>
    do_encode(t, new_bitstring)
  end

  def decode(dna), do: do_decode(dna, ~c"")

  defp do_decode(<<>>, decoded_charlist), do: decoded_charlist

  defp do_decode(<<first::4, rest::bitstring>>, decoded_charlist) do
    decoded = decode_nucleotide(first)
    new_charlist = decoded_charlist ++ [decoded]
    do_decode(rest, new_charlist)
  end
end
