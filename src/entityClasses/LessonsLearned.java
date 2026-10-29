package entityClasses;

/*******
 * <p> Title: LessonsLearned Class </p>
 * 
 * <p> Description: This LessonsLearned class represents a LessonsLearned entity in the system. It contains a lesson's
 *  contents such as the subject header, core information, and experience information. This class supports methods
 *  to access and modify the lessons' information.</p>
 * 
 * @author Noah Lesniewski
 * 
 */ 
public class LessonsLearned {
	
	/*
	 * These are the private attributes for this entity object
	 */
	private int id;
	private int lessonGroupID;
	private String username;
    private String lessonHeader;
    private String lessonBody;
    private boolean lessonHeaderLock;
    private boolean lessonBodyLock;
    private String whatWasDone;
    private String howItWasDone;
    private String timeSpent;
    private String effortUsed;
    private boolean infoVisibility;
    
    
    /*****
     * <p> Method: LessonsLearned(Integer lessonGroupID, String username, String lessonHeader, String lessonBody) </p>
     * 
     * <p> Description: This constructor is used for creating new lessons. The lessonGroupID is incremented based
     * on the highest group id found in the database whenever the createLessonLearned method is called. 
     * This makes every new lesson part of a unique group. Multiple lessons can have the same lessonGrupID, but they have the same 
     * header and core information, differing in experience information. The lesson header's and lesson body's locks are initially
     * false, making them unlocked, so the contributor can update the information freely until another role locks them. 
     * The whatWasDone, howItWasDone, timeSpent, and effortUsed parameters are empty since that information is added
     * later on. The infoVisibility is initially true, so experience information added later is automatically visible until
     * another role hides it. </p>
     * 
     * @param lessonGroupID specifies the lesson group id
     * 
     * @param username specifies the account username for the contributor of this lesson
     * 
     * @param lessonHeader contains the lesson subject header
     * 
     * @param lessonBody contains the lesson body core info
     */
    public LessonsLearned(int lessonGroupID, String username, String lessonHeader, String lessonBody) {
    	this.lessonGroupID = lessonGroupID;
        this.username = username;
        this.lessonHeader = lessonHeader;
        this.lessonBody = lessonBody;
        this.lessonHeaderLock = false;
        this.lessonBodyLock = false;
        this.whatWasDone = "";
        this.howItWasDone = "";
        this.timeSpent = "";
        this.effortUsed = "";
        this.infoVisibility = true; 
    }
    
    
    /*****
     * <p> Method: LessonsLearned(Integer id, Integer lessonGroupID, String username, String lessonHeader, 
     * String lessonBody, boolean lessonHeaderLock, boolean lessonBodyLock, String whatWasDone, String howItWasDone, 
     * String timeSpent, String effortUsed, boolean infoVisibility) </p>
     * 
     * <p> Description: This constructor is used for loading an already existing lesson. This constructor is used by the 
     * listLessonsLearned method and addExperienceInformation method in the LessonsLearnedList entity class. The 
     * listLessonsLearned method creates and populates an ObservableList of LessonsLearned objects using lesson details
     * stored in the database. The addExperienceInformation method uses the core information of an existing lesson 
     * to create a lesson containing new experience information provided by the user. </p>
     * 
     * @param id specifies the lesson id for this lesson
     * 
     * @param lessonGroupID specifies the lesson group id for this lesson
     * 
     * @param username specifies the account username for the contributor of this lesson
     * 
     * @param lessonHeader specifies the lesson subject title for this lesson
     * 
     * @param lessonBody specifies the lesson core information for this lesson
     * 
     * @param lessonHeaderLock specifies whether the lesson header is locked
     * 
     * @param lessonBodyLock specifies whether the lesson body is locked
     * 
     * @param whatWasDone specifies the experience information of what was done
     * 
     * @param howItWasDone specifies the experience information of how it was done
     * 
     * @param timeSpent specifies the experience information of time spent
     * 
     * @param effortUsed specifies the experience information of effort required by the team
     * 
     * @param infoVisibility specifies the visibility status for experience information
     */
    public LessonsLearned(int id, int lessonGroupID, String username, String lessonHeader, String lessonBody,
    		boolean lessonHeaderLock, boolean lessonBodyLock, String whatWasDone, String howItWasDone, 
    		String timeSpent, String effortUsed, boolean infoVisibility) {
    	this.id = id;
    	this.lessonGroupID = lessonGroupID;
        this.username = username;
        this.lessonHeader = lessonHeader;
        this.lessonBody = lessonBody;
        this.lessonHeaderLock = lessonHeaderLock;
        this.lessonBodyLock = lessonBodyLock;
        this.whatWasDone = whatWasDone;
        this.howItWasDone = howItWasDone;
        this.timeSpent = timeSpent;
        this.effortUsed = effortUsed;
        this.infoVisibility = infoVisibility; 
    }
    
    
    /*****
     * <p> Method: void setID(Integer i) </p>
     * 
     * <p> Description: This setter sets the ID. </p>
	 * 
	 * @param i specifies the ID to be set
     */
    public void setID(int i) { id = i; }
    
    
    /*****
     * <p> Method: void setLessonGroupID(Integer i) </p>
     * 
     * <p> Description: This setter sets the lessonGroupID. </p>
	 * 
	 * @param i specifies the lessonGroupID to be set
     */
    public void setLessonGroupID(int i) { lessonGroupID = i; }

    
    /*****
     * <p> Method: void setUsername(String s) </p>
     * 
     * <p> Description: This setter sets the username. </p>
	 * 
	 * @param s specifies the username to be set
     */
    public void setUsername(String s) { username = s; }
    
    
    /*****
     * <p> Method: void setLessonHeader(String s) </p>
     * 
     * <p> Description: This setter sets the lesson header. </p>
	 * 
	 * @param s specifies the lesson header to be set
     */
    public void setLessonHeader(String s) { lessonHeader = s; }
    
    
    /*****
     * <p> Method: void setLessonBody(String s) </p>
     * 
     * <p> Description: This setter sets the lesson body. </p>
	 * 
	 * @param s specifies the lesson body to be set
     */
    public void setLessonBody(String s) { lessonBody = s; }
    
    
    /*****
     * <p> Method: void setlessonHeaderLock(boolean b) </p>
     * 
     * <p> Description: This setter sets the lesson header lock. </p>
	 * 
	 * @param b specifies the lesson header lock to be set
     */
    public void setLessonHeaderLock(boolean b) { lessonHeaderLock = b; }
    
    
    /*****
     * <p> Method: void setlessonBodyLock(boolean b) </p>
     * 
     * <p> Description: This setter sets the lesson body lock. </p>
	 * 
	 * @param b specifies the lesson body lock to be set
     */
    public void setLessonBodyLock(boolean b) { lessonBodyLock = b; }
    
    
    /*****
     * <p> Method: void setWhatWasDone(String s) </p>
     * 
     * <p> Description: This setter sets what was done. </p>
	 * 
	 * @param s specifies what was done to be set
     */
    public void setWhatWasDone(String s) { whatWasDone = s; }
    
    
    /*****
     * <p> Method: void setHowItWasDone(String s) </p>
     * 
     * <p> Description: This setter sets how it was done. </p>
	 * 
	 * @param s specifies how it was done to be set
     */
    public void setHowItWasDone(String s) { howItWasDone = s; }
    
    
    /*****
     * <p> Method: void setTimeSpent(String s) </p>
     * 
     * <p> Description: This setter sets the time spent. </p>
	 * 
	 * @param s specifies the time spent to be set
     */
    public void setTimeSpent(String s) { timeSpent = s; }
    
    
    /*****
     * <p> Method: void setEffortUsed(String s) </p>
     * 
     * <p> Description: This setter sets effort used. </p>
	 * 
	 * @param s specifies the effort used to be set
     */
    public void setEffortUsed(String s) { effortUsed = s; }
    
    
    /*****
     * <p> Method: void setInfoVisibility(boolean b) </p>
     * 
     * <p> Description: This setter sets experience information visibility. </p>
	 * 
	 * @param b specifies the experience information visibility to be set
     */
    public void setInfoVisibility(boolean b) { infoVisibility = b; }
    
    
    /*****
     * <p> Method: Integer getID() </p>
     * 
     * <p> Description: This getter returns the lesson ID. </p>
     * 
     * @return an integer of the lesson ID
     * 
     */
    public int getID() { return id; }
    
    
    /*****
     * <p> Method: String getLessonGroupID() </p>
     * 
     * <p> Description: This getter returns the lesson group ID. </p>
     * 
     * @return an integer of the lesson group ID
     * 
     */
    public int getLessonGroupID() { return lessonGroupID; }
    
    
    /*****
     * <p> Method: String getUsername() </p>
     * 
     * <p> Description: This getter returns the Username. </p>
     * 
     * @return a String of the Username
     * 
     */
    public String getUsername() { return username; }
    
    
    /*****
     * <p> Method: String getLessonHeader() </p>
     * 
     * <p> Description: This getter returns the lesson header. </p>
     * 
     * @return a String of the lesson header
     * 
     */
    public String getLessonHeader() { return lessonHeader; }
    
    
    /*****
     * <p> Method: String getLessonBody() </p>
     * 
     * <p> Description: This getter returns the lesson body. </p>
     * 
     * @return a String of the lesson body
     * 
     */
    public String getLessonBody() { return lessonBody; }
    
    
    /*****
     * <p> Method: boolean getLessonHeaderLock() </p>
     * 
     * <p> Description: This getter returns the lesson header lock. </p>
     * 
     * @return a boolean of the lesson body lock
     * 
     */
    public boolean getLessonHeaderLock() { return lessonHeaderLock; }
    
    
    /*****
     * <p> Method: boolean getLessonBodyLock() </p>
     * 
     * <p> Description: This getter returns the lesson body lock. </p>
     * 
     * @return a boolean of the lesson body lock
     * 
     */
    public boolean getLessonBodyLock() { return lessonBodyLock; }
    
    
    /*****
     * <p> Method: String getWhatWasDone() </p>
     * 
     * <p> Description: This getter returns what was done. </p>
     * 
     * @return a String of what was done
     * 
     */
    public String getWhatWasDone() { return whatWasDone; }
    
    
    /*****
     * <p> Method: String getHowItWasDone() </p>
     * 
     * <p> Description: This getter returns how it was done. </p>
     * 
     * @return a String of how it was done
     * 
     */
    public String getHowItWasDone() { return howItWasDone; }
    
    
    /*****
     * <p> Method: String getTimeSpent() </p>
     * 
     * <p> Description: This getter returns the time spent. </p>
     * 
     * @return a String of time spent
     * 
     */
    public String getTimeSpent() { return timeSpent; }
    
    
    /*****
     * <p> Method: String getEffortUsed() </p>
     * 
     * <p> Description: This getter returns the effort used. </p>
     * 
     * @return a String of the effort used
     * 
     */
    public String getEffortUsed() { return effortUsed; }
    
    
    /*****
     * <p> Method: boolean getInfoVisibility() </p>
     * 
     * <p> Description: This getter returns the information visibility. </p>
     * 
     * @return a boolean of the information visibility
     * 
     */
    public boolean getInfoVisibility() { return infoVisibility; }
    
}