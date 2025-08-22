defmodule BoutiqueInventory do
  def sort_by_price(inventory) do
    Enum.sort_by(inventory, & &1.price)
  end

  def with_missing_price(inventory) do
    Enum.filter(inventory, &is_nil(&1.price))
  end

  def update_names(inventory, old_word, new_word) do
    inventory
    |> Enum.map(fn product ->
      updated_name =
        product.name
        |> String.replace(old_word, new_word)

      %{product | name: updated_name}
    end)
  end

  def increase_quantity(item, count) do
    new_quantity =
      item.quantity_by_size
      |> Map.new(fn {k, v} -> {k, v + count} end)

    %{item | quantity_by_size: new_quantity}
  end

  def total_quantity(item) do
    item.quantity_by_size
    |> Enum.reduce(0, fn {_k, v}, acc -> acc + v end)
  end
end
