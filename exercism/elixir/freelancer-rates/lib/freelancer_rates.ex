defmodule FreelancerRates do
  @hours_per_day 8.0
  @days_per_month 22

  def daily_rate(hourly_rate) do
    hourly_rate * @hours_per_day
  end

  def apply_discount(before_discount, discount) do
    before_discount * (1 - discount / 100)
  end

  def monthly_rate(hourly_rate, discount) do
    (daily_rate(hourly_rate) * @days_per_month)
    |> apply_discount(discount)
    |> ceil()
  end

  def days_in_budget(budget, hourly_rate, discount) do
    daily_rate(hourly_rate)
    |> apply_discount(discount)
    |> calculate_workdays(budget)
  end

  defp calculate_workdays(cost, budget) do
    (budget / cost)
    |> Float.floor(1)
  end
end
