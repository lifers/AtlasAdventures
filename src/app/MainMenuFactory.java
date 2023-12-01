package app;

import interface_adapter.answer_question.AnswerQuestionViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardPresenter;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfilePresenter;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.start_sp_quiz.SPQuizController;
import interface_adapter.start_sp_quiz.SPQuizPresenter;
import interface_adapter.start_sp_quiz.SPQuizViewModel;
import use_case.leaderboard.LeaderboardDataAccessInterface;
import use_case.leaderboard.LeaderboardInputBoundary;
import use_case.leaderboard.LeaderboardInteractor;
import use_case.leaderboard.LeaderboardOutputBoundary;
import use_case.profile.ProfileDataAccessInterface;
import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInteractor;
import use_case.start_sp_quiz.SPQuizDataAccessInterface;
import use_case.start_sp_quiz.SPQuizInputBoundary;
import use_case.start_sp_quiz.SPQuizInteractor;
import use_case.start_sp_quiz.SPQuizOutputBoundary;
import view.MainMenuView;

public class MainMenuFactory {
    private MainMenuFactory() {}

    public static MainMenuView create(
            ViewManagerModel viewManagerModel,
            SPQuizViewModel spQuizViewModel,
            SPQuizDataAccessInterface spQuizDAO,
            AnswerQuestionViewModel answerQuestionViewModel,
            ProfileViewModel profileViewModel,
            ProfileDataAccessInterface profileDAO,
            LeaderboardViewModel leaderboardViewModel,
            LeaderboardDataAccessInterface leaderboardDAO) {

        SPQuizController spQuizController = createSPQuizUseCase(viewManagerModel, spQuizViewModel, answerQuestionViewModel, spQuizDAO);
        ProfileController profileController = createProfileUseCase(viewManagerModel, profileViewModel, profileDAO);
        LeaderboardController leaderboardController = createLeaderboardUseCase(viewManagerModel, leaderboardViewModel, leaderboardDAO, profileDAO);
        return new MainMenuView(
                spQuizController, spQuizViewModel,
                leaderboardController, leaderboardViewModel,
                profileViewModel, profileController);
    }

    private static SPQuizController createSPQuizUseCase(ViewManagerModel viewManagerModel,
                                                        SPQuizViewModel spQuizViewModel,
                                                        AnswerQuestionViewModel questionViewModel,
                                                        SPQuizDataAccessInterface spQuizDAO) {
        SPQuizOutputBoundary spQuizPresenter = new SPQuizPresenter(viewManagerModel, spQuizViewModel, questionViewModel);

        SPQuizInputBoundary spQuizInteractor = new SPQuizInteractor(spQuizDAO, spQuizPresenter);

        return new SPQuizController(spQuizInteractor);
    }

    private static ProfileController createProfileUseCase(ViewManagerModel viewManagerModel,
                                                          ProfileViewModel profileViewModel,
                                                          ProfileDataAccessInterface DAO) {
        ProfilePresenter profilePresenter = new ProfilePresenter(profileViewModel, viewManagerModel);

        ProfileInputBoundary profileInteractor = new ProfileInteractor(profilePresenter, DAO);

        return new ProfileController(profileInteractor);

    }

    private static LeaderboardController createLeaderboardUseCase(ViewManagerModel viewManagerModel,
                                                                  LeaderboardViewModel leaderboardViewModel,
                                                                  LeaderboardDataAccessInterface leaderboardDAO,
                                                                  ProfileDataAccessInterface profileDAO) {
        LeaderboardOutputBoundary leaderboardPresenter = new LeaderboardPresenter(viewManagerModel, leaderboardViewModel);

        LeaderboardInputBoundary leaderboardInteractor = new LeaderboardInteractor(leaderboardPresenter, leaderboardDAO, profileDAO);

        return new LeaderboardController(leaderboardInteractor);
    }
}
