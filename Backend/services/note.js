const Notes = require("../models/note");


exports.findByUser = function (userId) {
	return Notes.findAll({
		where: {
			userId,
		},
	});
};


exports.findByCategory = function (categoryId) {
	return Notes.findAll({
		where: {
			categoryId,
		},
	});
};


exports.findAll = function () {
	return Notes.findAll();
};

exports.findById = function (id) {
	return Notes.findByPk(id);
};

exports.insert = function (data) {
	return Notes.create(data);
};

exports.update = async function (id, data) {
	await Notes.update(data, {
		where: {
			id,
		},
	});
};

exports.deleteById = async function (id) {
	const note = await Notes.findByPk(id);
	await note.destroy();
};

exports.blockById = async function (id) {
	await Notes.update(
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

exports.completeById = async function (id) {
	await Notes.update(
		{
			isCompleted: false,
		},
		{
			where: {
				id,
			},
		}
	);
};