<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lists of all Items</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
</head>
<body class="d-flex flex-column h-100">
<main class="flex-shrink-0">
    <div class="container">
        <h1 class="mt-5" style="color: seagreen">It's simple items pages<i>${appName}</i>.</h1>
        <p class="lead">The list of items: <strong class="d-inline-block mb-2 text-success">${items}</strong></p>
        <div class="col-md-6">
            <hr>
            <p>
                <a href="http://localhost:8080/">
                    <button type="back"> back</button>
                </a>
            </p>
        </div>
    </div>
    </div>
</main>
</body>
</html>