package mx.com.path.tipp.view.mvc;

import javax.validation.Valid;

import mx.com.path.tipp.control.TippSurveyServ;
import mx.com.path.tipp.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbookpro on 01/11/16.
 */
@Controller
@RequestMapping("/")
public class EvaluationController {

    @Autowired
    private TippSurveyServ tippSurveyServ;

    public void setTippSurveyServ(TippSurveyServ tippSurveyServ) {
        this.tippSurveyServ = tippSurveyServ;
    }

    @GetMapping(params = "bienvenida")
    public ModelAndView list() {

        return new ModelAndView("pages/bienvenida");
    }

    @GetMapping(params = "evaluacion")
    public ModelAndView createForm(@ModelAttribute Survey survey) {

        return new ModelAndView("pages/evaluacion","survey",tippSurveyServ.generateSurvey());

    }

    @PostMapping
    public HttpEntity<byte[]> create(@Valid Survey survey,
                                     BindingResult result,
                                     RedirectAttributes redirect) {

        byte[] documentBody = tippSurveyServ.getReportTipp(survey);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "pdf"));
        header.set("Content-Disposition",
                "attachment; filename=" + "TIPP.pdf".replace(" ", "_"));
        header.setContentLength(documentBody.length);

        return new HttpEntity<byte[]>(documentBody, header);

    }

}
