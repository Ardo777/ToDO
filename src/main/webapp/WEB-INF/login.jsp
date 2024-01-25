<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login and Register</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #007bff;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-bottom: 8px;
        }

        input {
            padding: 8px;
            margin-bottom: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div style="background: #007bff">
    <div class="container">
        <form id="loginForm" action="/login" method="post">
            <%if (session.getAttribute("msg") != null) { %>
            <span style="color: red;"><%=session.getAttribute("msg")%></span>
            <%session.removeAttribute("msg");%>
            <%}%>
            <label for="loginEmail">Email:</label>
            <input type="text" placeholder="Email" id="loginEmail" name="loginEmail" required>

            <label for="loginPassword">Password:</label>
            <input type="password" placeholder="Password" id="loginPassword" name="loginPassword" required>

            <button type="submit">Login</button>
        </form>

        <hr>

        <form id="registrationForm" action="/registration" method="post">
            <label for="username">Username:</label>
            <input type="text" placeholder="Username" id="username" name="username" required>
            <label for="email">Email:</label>
            <input type="text" placeholder="Email" id="email" name="email" required>
            <label for="password">Password:</label>
            <input type="password" placeholder="Registration Password" id="password" name="password"
                   required>

            <label for="confirmationPassword"> Confirmation Password:</label>
            <input type="password" placeholder="Confirmation Password" id="confirmationPassword"
                   name="confirmationPassword"
                   required>

            <button type="submit">Register</button>
        </form>
    </div>
</div>
</body>
</html>