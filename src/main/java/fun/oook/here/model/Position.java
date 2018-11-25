package fun.oook.here.model;

import java.io.Serializable;

/**
 * @author Joey
 * @date 2018-11-25
 * @since 1.0
 */
public final class Position implements Serializable {

    /**
     * Position
     */
    public static final String POSITION = "position";

    /**
     * Positions
     */
    public static final String POSITIONS = "positions";
    
    /**
     * Key of title.
     */
    public static final String POSITION_TITLE = "positionTitle";

    /**
     * Key of content.
     */
    public static final String POSITION_LNG = "lng";

    /**
     * Key of content.
     */
    public static final String POSITION_LAT = "lat";

    /**
     * Key of content.
     */
    public static final String POSITION_ADDRESS = "address";

    /**
     * Key of created at.
     */
    public static final String POSITION_CREATED = "positionCreated";

    /**
     * Key of create date.
     */
    public static final String POSITION_T_CREATE_DATE = "positionCreateDate";

    /**
     * Key of create time.
     */
    public static final String POSITION_CREATE_TIME = "positionCreateTime";

    /**
     * Key of updated at.
     */
    public static final String POSITION_UPDATED = "positionUpdated";

    /**
     * Key of update date.
     */
    public static final String POSITION_T_UPDATE_DATE = "positionUpdateDate";

    /**
     * Key of update time.
     */
    public static final String POSITION_UPDATE_TIME = "positionUpdateTime";

    /**
     * Key of tags.
     */
    public static final String POSITION_TAGS_REF = "positionTags";

    /**
     * Key of comment count.
     */
    public static final String POSITION_COMMENT_COUNT = "positionCommentCount";

    /**
     * Key of view count.
     */
    public static final String POSITION_VIEW_COUNT = "positionViewCount";

    /**
     * Key of comments.
     */
    public static final String POSITION_COMMENTS_REF = "positionComments";

    /**
     * Key of sign id.
     */
    public static final String POSITION_SIGN_ID = "positionSignId";

    /**
     * Key of permalink.
     */
    public static final String POSITION_PERMALINK = "positionPermalink";

    /**
     * Key of put top.
     */
    public static final String POSITION_PUT_TOP = "positionPutTop";

    /**
     * Key of is published.
     */
    public static final String POSITION_IS_PUBLISHED = "positionIsPublished";

    /**
     * Key of author id.
     */
    public static final String POSITION_AUTHOR_ID = "positionAuthorId";

    /**
     * Key of author email.
     */
    public static final String POSITION_T_AUTHOR_EMAIL = "positionAuthorEmail";

    /**
     * Key of had been published.
     */
    public static final String POSITION_HAD_BEEN_PUBLISHED = "positionHadBeenPublished";

    /**
     * Key of random double.
     */
    public static final String POSITION_RANDOM_DOUBLE = "positionRandomDouble";

    /**
     * Key of comment-able.
     */
    public static final String POSITION_COMMENTABLE = "positionCommentable";



    /**
     * Private constructor.
     */
    private Position() {
    }

    /**
     * Gets the abstract of the specified content.
     *
     * @param content the specified content
     * @return the abstract
     */
/*    public static String getAbstract(final String content) {
        final String plainTextContent = Jsoup.clean(Markdowns.toHTML(content), Whitelist.none());
        if (plainTextContent.length() > POSITION_ABSTRACT_LENGTH) {
            return plainTextContent.substring(0, POSITION_ABSTRACT_LENGTH) + "....";
        }

        return plainTextContent;
    }*/
}