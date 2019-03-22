package by.etc.shop.tag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;

import javax.servlet.jsp.JspWriter;

import javax.servlet.jsp.tagext.TagSupport;

import java.io.IOException;


@SuppressWarnings("serial")
public class LanguageTag extends TagSupport {

    private static final String LANGUAGE_PARAM = "language";
    private static final String RUSSIAN_LANGUAGE = "ru";
    private static final String CLOSE_TAG = "\">";
    private static final String CLOSE_LINK = "</a>";
    private String value;
    private String pageURL;

    public void setValue(String value) {
        this.value = value;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    public static final String CHANGE_TO_ENGLISH = "<a href=\"ChangeLanguage?Command=CHANGELANGUAGE&language=en&page=";
    public static final String CHANGE_TO_RUSSIAN = "<a href=\"ChangeLanguage?Command=CHANGELANGUAGE&language=ru&page=";

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();


        try {
            JspWriter out = pageContext.getOut();
            String currentLanguage = (String) session.getAttribute(LANGUAGE_PARAM);
            if (currentLanguage.equals(RUSSIAN_LANGUAGE)) {
                out.write(CHANGE_TO_ENGLISH + pageURL + CLOSE_TAG + value + CLOSE_LINK);
            } else {
                out.write(CHANGE_TO_RUSSIAN + pageURL + CLOSE_TAG + value + CLOSE_LINK);

            }

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}

