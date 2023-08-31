data class Lesson(
    val id: String = "",  // unique identifier for the lesson
    val lessonName: String,
    val content: String,
    val images: List<Int>,
    val topicId: String  // reference to the parent topic
)

data class Topics(
    val id: String = "",  // unique identifier for the topic
    val topicName: String,
    val subCourseId: String  // reference to the parent sub-course
)

data class SubCourse(
    val id: String = "",  // unique identifier for the sub-course
    val subCourseName: String,
    val courseId: String  // reference to the parent course
)

data class Courses(
    val id: String = "",  // unique identifier for the course
    val gradeId: String, // reference to the parent grade/class
    val courseName: String,
    val courseImage: Int
)

data class Classes(
    val id: String? = null,
    val name: String
)

data class UserActivity(
    val userId: String,
    val topicId: String,
    val timestamp: Long  // e.g., System.currentTimeMillis()
)

data class UserProfile(
    val userId: String,
    val name: String,
    val email: String,
    val profileImage: String? = null, // Could be a URL to the image in a cloud storage
    // Add any other needed attributes.
)

data class UserRecentCourse(
    val userId: String,
    val courseId: String,
    val timestamp: Long  // to order by recent access
)

data class QuizResult(
    val userId: String,
    val topicId: String,
    val score: Int,
    val totalQuestions: Int,
    val timestamp: Long  // to know when the quiz was taken
)