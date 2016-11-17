package mx.com.path.tipp.model.dao.impl;

import mx.com.path.tipp.model.dao.SurveyDao;
import mx.com.path.tipp.model.dto.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Repository;

/**
 * Created by macbookpro on 11/11/16.
 */
@Repository
public class SurveyDaoImpl implements SurveyDao {

    @Autowired
    private Survey survey;

    public Survey generateSurvey() {
        Survey surveyCloned = new Survey(survey);

        return surveyCloned;
    }

}
