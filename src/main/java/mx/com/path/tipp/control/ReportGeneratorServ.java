package mx.com.path.tipp.control;

import mx.com.path.tipp.model.dto.Survey;

/**
 * Created by macbookpro on 23/10/16.
 */
public interface ReportGeneratorServ {

    public byte[] generateReportTIPP(Survey survey);

}
