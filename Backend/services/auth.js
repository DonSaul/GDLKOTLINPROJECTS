const { findOneByEmail } = require("./user");
const { compare, sign } = require("./security");
const AuthException = require("../exceptions/auth");

exports.authenticate = async function (credentials) {
	const { email, password } = credentials;
	console.log('Email:', email);
	const user = await findOneByEmail(email);
	console.log('User:', user);
	if (!user) {
	  console.log('Usuario no encontrado');
	  throw new AuthException();
	}
	const isSame = await compare(password, user.password);
	console.log('Contraseña coincide:', isSame);
	if (!isSame) {
	  console.log('Contraseña incorrecta');
	  throw new AuthException();
	}
	return sign({ id: user.id });
  };