//<![CDATA[
function mascara(o, f) {
	v_obj = o;
	v_fun = f;
	setTimeout("execmascara()", 1);
}

function execmascara() {
	v_obj.value = v_fun(v_obj.value);
}

function valor(v) {
	v = v.replace(/\D/g, "");
	v = v.replace(/[0-9]{15}/, "Inválido");
	v = v.replace(/(\d{1})(\d{11})$/, "$1.$2"); // coloca ponto antes dos Ãºltimos 11 digitos
	v = v.replace(/(\d{1})(\d{8})$/, "$1.$2"); // coloca ponto antes dos Ãºltimos 8 digitos
	v = v.replace(/(\d{1})(\d{5})$/, "$1.$2"); // coloca ponto antes dos Ãºltimos 5 digitos
	v = v.replace(/(\d{1})(\d{1,2})$/, "$1,$2"); // coloca virgula antes dos Ãºltimos 2 digitos
	return v;
}

function quilometragem(v) {
	v = v.replace(/\D/g, "")
	v = v.replace(/[0-9]{11}/, "Inválido");

	if (v.length == 4) {
		v = v.replace(/(\d{1})(\d)/, "$1.$2");
	} else if (v.length == 5) {
		v = v.replace(/(\d{2})(\d)/, "$1.$2");
	} else if (v.length == 6) {
		v = v.replace(/(\d{3})(\d)/, "$1.$2");
	} else if (v.length == 7) {
		v = v.replace(/(\d{4})(\d)/, "$1.$2");
	} else if (v.length == 8) {
		v = v.replace(/(\d{5})(\d)/, "$1.$2");
	} else if (v.length == 9) {
		v = v.replace(/(\d{6})(\d)/, "$1.$2");
	} else if (v.length == 10) {
		v = v.replace(/(\d{7})(\d)/, "$1.$2");
	}
	return v;
}

function cpfCnpj(v) {
	// Remove tudo o que não é dígito
	v = v.replace(/\D/g, "");
	v = v.replace(/[0-9]{15}/, "Inválido");
	if (v.length == 11) { // CPF

		// Coloca um ponto entre o terceiro e o quarto dígitos
		v = v.replace(/(\d{3})(\d)/, "$1.$2");

		// Coloca um ponto entre o terceiro e o quarto dígitos de novo (para o segundo bloco de números)
		v = v.replace(/(\d{3})(\d)/, "$1.$2");

		// Coloca um hífen entre o terceiro e o quarto dígitos
		v = v.replace(/(\d{3})(\d{1,2})$/, "$1-$2");

	} else { // CNPJ

		// Coloca ponto entre o segundo e o terceiro dígitos
		v = v.replace(/^(\d{2})(\d)/, "$1.$2");

		// Coloca ponto entre o quinto e o sexto dígitos
		v = v.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3");

		// Coloca uma barra entre o oitavo e o nono dígitos
		v = v.replace(/\.(\d{3})(\d)/, ".$1/$2");

		// Coloca um hífen depois do bloco de quatro dígitos
		v = v.replace(/(\d{4})(\d)/, "$1-$2");

	}
	return v
}

function Cnpj(v) {
	v = v.replace(/\D/g, "")
	v = v.replace(/[0-9]{15}/, "Inválido");
	v = v.replace(/^(\d{2})(\d)/, "$1.$2")
	v = v.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3")
	v = v.replace(/\.(\d{3})(\d)/, ".$1/$2")
	v = v.replace(/(\d{4})(\d)/, "$1-$2")
	return v
}

function Cpf(v) {
	v = v.replace(/\D/g, "")
	v = v.replace(/[0-9]{12}/, "Inválido");
	v = v.replace(/(\d{3})(\d)/, "$1.$2")
	v = v.replace(/(\d{3})(\d)/, "$1.$2")
	v = v.replace(/(\d{3})(\d{1,2})$/, "$1-$2")
	return v
}

function Cedente(v) {
	v = v.replace(/\D/g, "")
	v = v.replace(/[0-9]{11}/, "Inválido");

	if (v.length == 4) {
		v = v.replace(/(\d{3})(\d)/, "$1-$2")

	} else if (v.length == 5) {
		v = v.replace(/(\d{1})(\d)/, "$1.$2")
		v = v.replace(/(\d{3})(\d)/, "$1-$2")

	} else if (v.length == 6) {
		v = v.replace(/(\d{2})(\d)/, "$1.$2")
		v = v.replace(/(\d{3})(\d)/, "$1-$2")

	} else if (v.length == 7) {
		v = v.replace(/(\d{3})(\d)/, "$1.$2")
		v = v.replace(/(\d{3})(\d)/, "$1.$2")

	} else if (v.length == 8) {
		v = v.replace(/(\d{1})(\d)/, "$1.$2")
		v = v.replace(/(\d{3})(\d)/, "$1-$2")
		v = v.replace(/(\d{3})(\d)/, "$1-$2")

	} else if (v.length == 9) {
		v = v.replace(/(\d{2})(\d)/, "$1.$2")
		v = v.replace(/(\d{3})(\d)/, "$1-$2")
		v = v.replace(/(\d{3})(\d)/, "$1-$2")

	} else if (v.length == 10) {
		v = v.replace(/(\d{3})(\d)/, "$1.$2")
		v = v.replace(/(\d{3})(\d)/, "$1.$2")
		v = v.replace(/(\d{3})(\d)/, "$1-$2")
	}
	return v
}

function porcentagem(v) {
	v = v.replace(/\D/g, "")
	v = v.replace(/[0-9]{5}/, "Inválido");

	if (v.length == 2) {
		v = v.replace(/(\d{2})(\d)/, "$1.$2")

	} else if (v.length == 3 && v == 100) {
		v = v.replace(/(\d{3})(\d)/, "$1.$2")

	} else if (v.length == 3 && v > 100) {
		v = v.replace(/(\d{1})(\d)/, "$1.$2")

	} else if (v.length == 4) {
		v = v.replace(/(\d{2})(\d)/, "$1.$2")
	}
	return v
}

function formatarCampo(campoTexto) {
	if (campoTexto.value.length <= 11) {
		campoTexto.value = mascaraCpf(campoTexto.value);
	} else {
		campoTexto.value = mascaraCnpj(campoTexto.value);
	}
}
function retirarFormatacao(campoTexto) {
	campoTexto.value = campoTexto.value.replace(/(\.|\/|\-)/g, "");
}
function mascaraCpf(valor) {
	return valor.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g, "\$1.\$2.\$3\-\$4");
}
function mascaraCnpj(valor) {
	return valor.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/g,
			"\$1.\$2.\$3\/\$4\-\$5");
}

PrimeFaces.widget.Menubar.prototype.activate = function(b) {
	this.highlight(b);
	var a = b.children("ul.ui-menu-child");
	if (a.length == 1) {
		b.parent().css('display', 'table')
		this.showSubmenu(b, a)
	} else {
		b.parent().css('display', 'block')
	}
}

// ]]>
