package services;

import services.Answer;

import java.sql.SQLException;
import java.util.Map;

public interface ValidatorService{
    Answer validate(Map<String,String> params) throws SQLException;
}
