package mx.com.path.tipp.model.dto;

/**
 * Created by macbookpro on 21/10/16.
 */
public class Question {

    public Question() {
    }

    public Question(String question) {
        this.question = question;
    }

    public Question(String wordQuestion, Boolean flagWordQuestion) {
        this.wordQuestion = wordQuestion;
        this.flagWordQuestion = flagWordQuestion;
    }

    private String question;
    private String wordQuestion;
    private boolean flagWordQuestion;
    private boolean answerBoolean;
    private String answerText;
    private String comment;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean getAnswerBoolean() {
        return answerBoolean;
    }

    public void setAnswerBoolean(boolean answerBoolean) {
        this.answerBoolean = answerBoolean;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getWordQuestion() {
        return wordQuestion;
    }

    public void setWordQuestion(String wordQuestion) {
        this.wordQuestion = wordQuestion;
    }

    public boolean getFlagWordQuestion() {
        return flagWordQuestion;
    }

    public void setFlagWordQuestion(boolean flagWordQuestion) {
        this.flagWordQuestion = flagWordQuestion;
    }
}
