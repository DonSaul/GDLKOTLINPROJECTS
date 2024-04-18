const Joi = require("joi");

exports.createNoteSchema = Joi.object({
	title: Joi.string().min(5).max(100).required(),
	content: Joi.string().min(5).max(2500).required()
});

exports.updateNoteSchema = Joi.object({
	title: Joi.string().min(5).max(100).required(),
	content: Joi.string().min(5).max(2500).required()
}).min(1);

exports.paramsSchema = Joi.object({
	id: Joi.number().required(),
});