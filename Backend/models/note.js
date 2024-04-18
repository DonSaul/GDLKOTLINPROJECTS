const { DataTypes } = require("sequelize");
const { sequelize } = require("./sequelize");

module.exports = sequelize.define("notes", {
	title: {
		type: DataTypes.STRING(100),
		allowNull: false,
		validate: {
			len: [5, 100],
		},
	},
	content: {
		type: DataTypes.TEXT,
		allowNull: false,
		validate: {
			len: [5, 2500],
		},
	},
	isActive: {
		field: "is_active",
		type: DataTypes.BOOLEAN,
		allowNull: false,
		defaultValue: true,
	},
	isCompleted: {
		type: DataTypes.BOOLEAN,
		defaultValue: false,
		field: "is_completed",
		allowNull: false,
	},
});