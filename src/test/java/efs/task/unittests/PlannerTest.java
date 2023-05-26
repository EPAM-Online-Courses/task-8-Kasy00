package efs.task.unittests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class PlannerTest {
    private Planner planner;

    @BeforeEach
    void setPlanner(){
        planner = new Planner();
    }

    @ParameterizedTest
    @EnumSource(ActivityLevel.class)
    void shouldReturnCorrectDailyCaloriesDemandOnActivityLevel(ActivityLevel activityLevel){
        //given
        User user = TestConstants.TEST_USER;
        int expectedCalories = TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel);

        //when
        int actualCalories = planner.calculateDailyCaloriesDemand(user, activityLevel);

        //then
        assertEquals(expectedCalories, actualCalories);
    }

    @Test
    void shouldReturnCorrectDailyIntakes(){
        //given
        User user = TestConstants.TEST_USER;
        DailyIntake expectedIntakes = TestConstants.TEST_USER_DAILY_INTAKE;

        //when
        DailyIntake actualIntakes = planner.calculateDailyIntake(user);

        //then
        assertEquals(expectedIntakes.getCalories(), actualIntakes.getCalories());
        assertEquals(expectedIntakes.getCarbohydrate(), actualIntakes.getCarbohydrate());
        assertEquals(expectedIntakes.getFat(),actualIntakes.getFat());
        assertEquals(expectedIntakes.getProtein(), actualIntakes.getProtein());
    }

}
