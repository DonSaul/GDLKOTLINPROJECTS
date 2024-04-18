const { insert } = require("../services/user");

exports.createUser = async function (request, response) {
	const { email, password } = request.body;
	const user = await insert({ email, password });
	response.status(201).json(user);
};