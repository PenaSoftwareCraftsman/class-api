mongo classes --eval 'db.user.insert({
    name: "SECRETARY",
    password: "$2a$10$jfzv1bqAaYUQSvGJxUf/lu.WREBZF8bmBs8eVbS5pbdQRE0CA8YAG",
    email: "secretary@example.com",
    role: "SECRETARY"
})'