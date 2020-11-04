package services;

import entities.Achievement;
import entities.Action;
import entities.ShopBooster;

import java.sql.SQLException;
import java.util.Collection;

public interface AchievementService{
    void addAction(int userId, Action action) throws SQLException;
    <T extends Action>  Collection<T> getUserActions(int userId) throws SQLException;
    <T extends Achievement> Collection<T> getAchievements() throws SQLException;
    <T extends Achievement> Collection<T> getAchievementsWithProgress(int userId) throws SQLException;
    <T extends Achievement> Collection<T> getUserAchievements(int userId) throws SQLException;
    void updateUserAchievements(int userId) throws SQLException;
    void deleteAchievement(int achievementId) throws SQLException;
    void addAchievement(Achievement achievement) throws SQLException;
    void updateAchievement(Achievement achievement)throws SQLException;
}
