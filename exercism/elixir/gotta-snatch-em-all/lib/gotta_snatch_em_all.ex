defmodule GottaSnatchEmAll do
  @type card :: String.t()
  @type collection :: MapSet.t(card())

  @spec new_collection(card()) :: collection()
  def new_collection(card) do
    MapSet.new([card])
  end

  @spec add_card(card(), collection()) :: {boolean(), collection()}
  def add_card(card, collection) do
    member? = MapSet.member?(collection, card)
    {member?, MapSet.put(collection, card)}
  end

  @spec trade_card(card(), card(), collection()) :: {boolean(), collection()}
  def trade_card(your_card, their_card, collection) do
    possible? =
      MapSet.member?(collection, your_card) and not MapSet.member?(collection, their_card)

    {possible?, MapSet.put(collection, their_card) |> MapSet.delete(your_card)}
  end

  @spec remove_duplicates([card()]) :: [card()]
  def remove_duplicates(cards) do
    cards
    |> MapSet.new()
    |> Enum.sort()
  end

  @spec extra_cards(collection(), collection()) :: non_neg_integer()
  def extra_cards(your_collection, their_collection) do
    MapSet.difference(your_collection, their_collection)
    |> MapSet.size()
  end

  @spec boring_cards([collection()]) :: [card()]
  def boring_cards([]), do: []

  def boring_cards(collections) do
    collections
    |> Enum.reduce(&MapSet.intersection/2)
    |> MapSet.to_list()
  end

  @spec total_cards([collection()]) :: non_neg_integer()
  def total_cards(collections) do
    collections
    |> Enum.reduce(MapSet.new(), &MapSet.union/2)
    |> MapSet.size()
  end

  @spec split_shiny_cards(collection()) :: {[card()], [card()]}
  def split_shiny_cards(collection) do
    {shiny, non_shiny} =
      collection
      |> MapSet.split_with(fn item -> String.starts_with?(item, "Shiny") end)

    {MapSet.to_list(shiny), MapSet.to_list(non_shiny)}
  end
end
