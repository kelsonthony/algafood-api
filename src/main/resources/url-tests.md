http://localhost:8081/oauth/authorize?response_type=code&client_id=foodanalytics&state=abc&redirect_uri=http://aplicacao-cliente

http://localhost:8081/oauth/authorize?response_type=code&client_id=foodanalytics&state=abc&redirect_uri=http://127.0.0.1:5500

http://aplicacao-cliente/?code=7EWxiX&state=abc

http://aplicacao-cliente/?code=lAEMWJ&state=abc

Implicit Grant

http://localhost:8081/oauth/authorize?response_type=token&client_id=webadmin&state=abc&redirect_uri=http://127.0.0.1:5500

http://127.0.0.1:5500/#access_token=81a0672f-4a59-4a69-aa6e-fcecb6280a28&token_type=bearer&state=abc&expires_in=43082&scope=read%20write

Fluxo Authorization Code com PKCE com o método plain

Authorization Code

http://localhost:8080/oauth/authorize?response_type=code&client_id=foodanalytics&state=abc&redirect_uri=http://127.0.0.1:5500/foodanalytucs-client-authorizationcode/index.html

Login oauth/token
http://localhost:8080/login

http://localhost:8081/oauth/authorize?response_type=code&client_id=foodanalytics&redirect_uri=http://127.0.0.1:5500

With Authorization Code with PKCE

Code Verifier: teste123
Code Challenge: teste123

http://localhost:8081/oauth/authorize?response_type=code&client_id=foodanalytics&code_challenge=teste123&code_challenge_method=plain&redirect_uri=http://127.0.0.1:5500

http://localhost:8081/oauth/authorize?response_type=code&client_id=foodanalytics&code_challenge=teste1234&code_challenge_method=plain&redirect_uri=http://aplicacao-cliente

http://localhost:8081/oauth/authorize?response_type=code&client_id=foodanalytics&code_challenge=teste1234&code_challenge_method=plain&redirect_uri=http://aplicacao-cliente

Fluxo Authorization Code com PKCE com o método SHA 256

Code Verifier:pU79oWNpOttEzcdUjk6I0rDP95zwTzZLf45dT1s~APkM.uTJfMJIfIf2z5rZ2Nb14ecKvHs3p4aoSbiBmu.V9Dnt6bnG2MZ7.nyNITam7R.kfdjoPi465n_dEouqfDYY

Code Challenge: base64url(sha256("pU79oWNpOttEzcdUjk6I0rDP95zwTzZLf45dT1s~APkM.uTJfMJIfIf2z5rZ2Nb14ecKvHs3p4aoSbiBmu.V9Dnt6bnG2MZ7.nyNITam7R.kfdjoPi465n_dEouqfDYY"))

Real Code Challenge: CrnILUCbOXeDEJISgjpESlQdAXUidyzON6At9WUSBa8

http://localhost:8081/oauth/authorize?response_type=code&client_id=foodanalytics&code_challenge=CrnILUCbOXeDEJISgjpESlQdAXUidyzON6At9WUSBa8&code_challenge_method=s256&redirect_uri=http://aplicacao-cliente
