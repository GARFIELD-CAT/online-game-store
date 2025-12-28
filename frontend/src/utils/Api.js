class Api {
  constructor() {
    this.baseUrl = "http://localhost:8080/";
    this._headers = {
        "Content-Type": "application/json"
    };
  }

  async loginUser(email, password) {
    const url = `${this.baseUrl}auth/login`;

    let response = await fetch(`${url}`, {
        method: "POST",
        headers: this._headers,
        body: JSON.stringify({"email": email, "password": password})
    });

    if (response.ok) {
        let json = await response.json();
        return json;
    } else {
        return Promise.reject(`Ошибка: ${response.status}`);
    }
  }

  async getGames(token) {
    const url = new URL(`${this.baseUrl}api/v1/games`);
    const bearerToken = token === null ? localStorage.getItem('jwtToken') : token

    let response = await fetch(`${url}`, {
            method: "GET",
            headers: {...this._headers, "Authorization": `Bearer ${bearerToken}`}
      });

        if (response.ok) {
            let json = await response.json();
            return json;
        } else {
            return Promise.reject(`Ошибка: ${response.status}`);
        }
  }

  async createOrder(data) {
    const url = `${this.baseUrl}api/v1/game-orders`;
    const bearerToken = localStorage.getItem('jwtToken')

    let response = await fetch(`${url}`, {
            method: "POST",
            headers: {...this._headers, "Authorization": `Bearer ${bearerToken}`},
            body: JSON.stringify({ "userId": 2, "game_ids": data })
        });

        if (response.ok) {
            let json = await response.json();
            return json;
        } else {
            return Promise.reject(`Ошибка: ${response.status}`);
        }
  }

  async getOrder(id) {
      const url = new URL(`${this.baseUrl}api/v1/game-orders/${id}`);
      const bearerToken = localStorage.getItem('jwtToken')

      let response = await fetch(`${url}`, {
              method: "GET",
              headers: {...this._headers, "Authorization": `Bearer ${bearerToken}`}
        });

          if (response.ok) {
              let json = await response.json();
              return json;
          } else {
              return Promise.reject(`Ошибка: ${response.status}`);
          }
    }

  async createPayment(data) {
      const url = `${this.baseUrl}external-api/v1/payments`;
      const bearerToken = localStorage.getItem('jwtToken')

      let response = await fetch(`${url}`, {
              method: "POST",
              headers: {...this._headers, "Authorization": `Bearer ${bearerToken}`},
              body: JSON.stringify({"game_ids": data })
          });

          if (response.ok) {
              let json = await response.json();
              return json;
          } else {
              return Promise.reject(`Ошибка: ${response.status}`);
          }
    }

}

export default new Api();
