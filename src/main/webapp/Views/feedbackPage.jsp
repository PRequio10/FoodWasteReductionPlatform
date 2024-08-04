<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Feedback</title>
    <link rel="stylesheet" type="text/css" href="feedbackPage.css">
    <script type="text/javascript">
        function showSubmissionAlert() {
            alert("Thank you for your feedback! It has been submitted successfully.");
        }
    </script>
</head>
<body>
    <header>
        <h1>Feedback Page</h1>
    </header>

    <div class="container">
        <div class="introduction">
            <h2>We Value Your Feedback</h2>
            <p>Please let us know your thoughts, suggestions, or concerns. Your feedback helps us improve.</p>
        </div>

        <form action="<%= request.getContextPath() %>/submitFeedback" method="POST" onsubmit="showSubmissionAlert()">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required style="width: 25%;">

            <label for="message">Your Feedback:</label>
            <textarea id="message" name="message" rows="10" required></textarea>

            <div style="text-align: center;">
    		<button type="submit" style="width: 25%;">Submit Feedback</button>
			</div>	
        </form>
    </div>

    <footer>
        <p>&copy; 2024 Food Waste Reduction Platform</p>
    </footer>
</body>
</html>
