package mx.com.path.tipp.control.impl;

import mx.com.path.tipp.control.ReportGeneratorServ;
import mx.com.path.tipp.control.TippSurveyServ;
import mx.com.path.tipp.model.dao.SurveyDao;
import mx.com.path.tipp.model.dto.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbookpro on 23/10/16.
 */
@Component
public class TippSurveyServImpl implements TippSurveyServ {

    @Autowired
    ReportGeneratorServ reportGeneratorServ;

    @Autowired
    SurveyDao surveyDao;

    public byte[] getReportTipp(Survey surveyAnswers) {

        Survey surveyQuestions = surveyDao.generateSurvey();

        copyPropertiesSections(surveyAnswers.getSectionSensitives(),
                surveyQuestions.getSectionSensitives(),
                80);
        copyPropertiesSections(surveyAnswers.getSectionContac(),
                surveyQuestions.getSectionContac(),
                0);
        copyPropertiesSections(surveyAnswers.getSectionIntelligence(),
                surveyQuestions.getSectionIntelligence(),
                0);
        copyPropertiesSections(surveyAnswers.getSectionCognitive(),
                surveyQuestions.getSectionCognitive(),
                0);
        copyPropertiesSections(surveyAnswers.getSectionsProfile(),
                surveyQuestions.getSectionsProfile(),
                0);

        BeanUtils.copyProperties(surveyAnswers,
                                 surveyQuestions,
                                 "sectionSensitives",
                                 "sectionContac",
                                 "sectionIntelligence",
                                 "sectionCognitive",
                                 "sectionsProfile");

        byte[] reportTipp = reportGeneratorServ.generateReportTIPP(surveyQuestions);

        return reportTipp;
    }

    @Override
    public Survey generateSurvey() {
        return surveyDao.generateSurvey();
    }

    private void copyPropertiesSections(List<Section> sectionAnswers,
                                        List<Section> sectionQuestions,
                                        Integer minimum){
        int indexSectionAnswer = 0;
        List<Integer> sectionToDelete = new ArrayList<Integer>();
        for(Section sectionSensitiveAnswer : sectionAnswers){

            if(sectionSensitiveAnswer.getPorcentage() >= minimum){
                List<Question> questionariesAnswer = sectionSensitiveAnswer.getQuestions();
                Section sectionSensitiveQuestions = sectionQuestions.get(indexSectionAnswer);
                List<Question> questionariesQuestions = sectionSensitiveQuestions.getQuestions();

                int indexQuestionaryAnswer = 0;
                for(Question questionQuestions : questionariesQuestions){
                    Question questionAnswer = questionariesAnswer.get(indexQuestionaryAnswer++);
                    BeanUtils.copyProperties(questionAnswer, questionQuestions,"question","wordQuestion","flagWordQuestion");
                }

                BeanUtils.copyProperties(sectionSensitiveAnswer,
                        sectionSensitiveQuestions,
                        "title",
                        "details1",
                        "details2",
                        "questions",
                        "justWord",
                        "shorName",
                        "specificName");
            }else {
                sectionToDelete.add(indexSectionAnswer);
            }
            indexSectionAnswer++;
        }
        for (int index = sectionToDelete.size() - 1; index >= 0; index--){
            sectionQuestions.remove(sectionToDelete.get(index).intValue());
            sectionAnswers.remove(sectionToDelete.get(index).intValue());
        }
    }

}
