defmodule KitchenCalculator do
  @cup_ml 240
  @fluid_ounce_ml 30
  @teaspoon_ml 5
  @tablespoon_ml 15

  def get_volume({_, volume}), do: volume

  def to_milliliter({:milliliter, volume}), do: {:milliliter, volume}
  def to_milliliter({:cup, volume}), do: {:milliliter, volume * @cup_ml}
  def to_milliliter({:fluid_ounce, volume}), do: {:milliliter, volume * @fluid_ounce_ml}
  def to_milliliter({:teaspoon, volume}), do: {:milliliter, volume * @teaspoon_ml}
  def to_milliliter({:tablespoon, volume}), do: {:milliliter, volume * @tablespoon_ml}

  def from_milliliter({_, ml}, :milliliter), do: {:milliliter, ml}
  def from_milliliter({_, ml}, :cup), do: {:cup, ml / @cup_ml}
  def from_milliliter({_, ml}, :fluid_ounce), do: {:fluid_ounce, ml / @fluid_ounce_ml}
  def from_milliliter({_, ml}, :teaspoon), do: {:teaspoon, ml / @teaspoon_ml}
  def from_milliliter({_, ml}, :tablespoon), do: {:tablespoon, ml / @tablespoon_ml}

  def convert(volume_pair, unit) do
    volume_pair
    |> to_milliliter()
    |> from_milliliter(unit)
  end
end
