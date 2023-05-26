package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void shouldReturnFalse_whenDietNotRecommended() {
        //given
        double weight = 69.5;
        double height = 1.72;

        //when
        boolean not_recommended = FitCalculator.isBMICorrect(weight,height);

        //then
        assertFalse(not_recommended);
    }

    @Test
    void shouldThrowIllegalArgumentException_ifHeightIsZero(){
        //given
        double weight = 84;
        double height = 0.0;

        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> FitCalculator.isBMICorrect(weight, height));
    }

    @ParameterizedTest(name = "Test {index}: weight={0}")
    @ValueSource(doubles = {1.70, 1.75, 1.76})
    void shouldReturnTrue_forValidWeight(double height) {
        //given
        double weight = 80.0;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @ParameterizedTest(name = "Test {index}: height={0}, weight={1}")
    @CsvSource({"1.70, 65.8","1.88, 84.4", "1.95, 90.0"})
    void shouldReturnFalse_forEveryPair(double height, double weight){
        //given

        //when
        boolean pair = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(pair);
    }

    @ParameterizedTest(name = "Test {index}: height={0}, weight={1}")
    @CsvFileSource(resources = "resources.data.csv")
    void shouldReturnFalse_forInvalidPair(double height, double weight){
        //given

        //when
        boolean pair = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(pair);
    }

    @Test
    void shouldReturnUserWithWorstBMI(){
        //given
        List<User> users = TestConstants.TEST_USERS_LIST;
        User expectedUser = new User(1.79, 97.3);

        //when
        User resultUser = FitCalculator.findUserWithTheWorstBMI(users);

        // then
        assertAll("User properties",
                () -> assertEquals(expectedUser.getWeight(), resultUser.getWeight()),
                () -> assertEquals(expectedUser.getHeight(), resultUser.getHeight())
        );
    }

    @Test
    void shouldReturnNull_whenListEmpty(){
        //given
        List<User> emptyUserList = new ArrayList<>();

        //when
        User empty = FitCalculator.findUserWithTheWorstBMI(emptyUserList);

        //then
        assertNull(empty);
    }

    @Test
    void shouldCalculateBMIScoreForUsersList(){
        //given
        List<User> users = TestConstants.TEST_USERS_LIST;
        double[] expectedBMIScores = TestConstants.TEST_USERS_BMI_SCORE;

        //when
        double[] actualBMIScores = FitCalculator.calculateBMIScore(users);

        //then
        assertArrayEquals(expectedBMIScores, actualBMIScores);
    }

}
