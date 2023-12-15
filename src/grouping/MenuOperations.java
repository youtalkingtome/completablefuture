package grouping;

import java.util.Optional;

import static java.util.stream.Collectors.reducing;

public class MenuOperations {


    public int getTotalCalories() {
        int totalCalories = Menu.menu.stream().collect(reducing(
                0, Dish::getCalories, (i, j) -> i + j));
        return totalCalories;
    }
    public Optional<Dish> highestCalorieDish()
    {
        Optional<Dish> mostCalorieDish =
                Menu.menu.stream().collect(reducing(
                        (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        return mostCalorieDish;
    }


}
