package nl.igf.employees;

public interface IChef {

    // final by default
    String favoriteFood = "pasta";

    default void cook(String food) {
        System.out.println(food);
    }

    default String cleanUp() {
        return "I'm done with dish washing...";
    }

    default String getFavoriteFood() {
        return favoriteFood;
    }
}
