const User = require("./models/user");
const Note = require("./models/note");
const Category = require("./models/category");
const { connect, sync } = require("./models/sequelize");

exports.initDatabase = async function () {
	// Un usuario crea muchas notas
	// Pero una nota pertenece a un solo usuario
	User.hasMany(Note);
	Note.belongsTo(User);

	Category.hasMany(Note);
	Note.belongsTo(Category);

	User.hasMany(Category);
	Category.belongsTo(User);

	await connect();
	await sync();
};