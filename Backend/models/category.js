const { DataTypes } = require("sequelize");
const { sequelize } = require("./sequelize");

module.exports = sequelize.define("category", {
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
    icon: {
		type: DataTypes.TEXT,
		allowNull: true
	},
});