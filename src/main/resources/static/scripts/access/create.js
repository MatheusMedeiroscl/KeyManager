const URL = "http://localhost:8080/client";

let txtLogin = document.getElementById("txtLogin");
let txtPass = document.getElementById("txtPass");

const form = document.getElementById("createLogin");

form.addEventListener("submit", (e) => {
    e.preventDefault();

    const loginData = {
        login: txtLogin.value,
        password: txtPass.value
    };

    async function create() {
        try {
            const response = await fetch(URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(loginData),
            });

            if (!response.ok) {
                throw new Error(`ERRO AO FAZER LOGIN: ${response.status} - ${response.statusText}`);
            }

            alert("CONTA CRIADA COM SUCESSO!")
            window.location.href = `access.html`;
        } catch (error) {
            console.error("ERRO EM FAZER A REQ DE LOGIN:", error);
            alert("Login ou senha inválidos!");
        }
    }

    create();
});
