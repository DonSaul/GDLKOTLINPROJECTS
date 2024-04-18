const { DataTypes } = require("sequelize");
const { sequelize } = require("./sequelize");

module.exports = sequelize.define("users", {
	email: {
		type: DataTypes.STRING(100),
		allowNull: false,
		unique: true,
	},
	username: {
		type: DataTypes.STRING(100),
		allowNull: true,
		unique: true,
	},
	password: {
		type: DataTypes.STRING,
		allowNull: false,
	},
	isActive: {
		field: "is_active",
		type: DataTypes.BOOLEAN,
		allowNull: false,
		defaultValue: true,
	},
});