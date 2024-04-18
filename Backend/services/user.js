const User = require("../models/user");
const { hash } = require("./security");



exports.insert = async function (data) {
	data.password = await hash(data.password);
	const user = await User.create(data);
	delete user.dataValues.password;
	return user;
};


exports.findByUsername = function (username) {
	// SELECT * FROM users WHERE username = '...';
	return User.findOne({
		where: {
			username,
		},
	});
};

exports.findOneByEmail = function (email) {
	return User.findOne({
		where: {
			email,
		},
	});
};

exports.findById = function (id) {
	// SELECT * FROM users WHERE id = '...';
	return User.findByPk(id);
};

exports.blockById = async function (id) {
	await User.update(
		{
			isActive: false,
		},
		{
			where: {
				id,
			},
		}
	);
};

exports.findOneByPayload = async function (payload, done) {
	const user = await User.findByPk(payload.id);

	if (!user) {
		return done({ message: "El usuario no existe en la base de datos" });
	}

	// Se crea el request.user
	done(null, user);
};