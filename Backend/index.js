require("dotenv").config();

const { initDatabase } = require("./db");
initDatabase();

const express = require("express");
const app = express();

app.use(express.json());

const userRouter = require("./routers/user");
const noteRouter = require("./routers/note");
const authRouter = require("./routers/auth");
const categoryRouter = require("./routers/category");

app.use( userRouter);
app.use( noteRouter);
app.use( authRouter);
app.use( categoryRouter);

const validationError = require("./middlewares/validation-error");
const unknownError = require("./middlewares/unknown-error");
// Manejo de errores
app.use(validationError);
app.use(unknownError);

app.listen(process.env.SERVER_PORT || 3000, function () {
	console.log("> Servidor escuchando peticiones");
});