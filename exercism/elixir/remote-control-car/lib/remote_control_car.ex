defmodule RemoteControlCar do
  @enforce_keys [:nickname]
  defstruct [:nickname, battery_percentage: 100, distance_driven_in_meters: 0]

  def new(nickname \\ "none"), do: %RemoteControlCar{nickname: nickname}

  def display_distance(%RemoteControlCar{distance_driven_in_meters: distance}),
    do: "#{distance} meters"

  def display_battery(%RemoteControlCar{battery_percentage: 0}), do: "Battery empty"

  def display_battery(%RemoteControlCar{battery_percentage: battery}),
    do: "Battery at #{battery}%"

  def drive(
        %RemoteControlCar{battery_percentage: battery, distance_driven_in_meters: distance} = car
      )
      when battery > 0 do
    %RemoteControlCar{
      car
      | battery_percentage: battery - 1,
        distance_driven_in_meters: distance + 20
    }
  end

  def drive(%RemoteControlCar{} = car), do: car
end
