# Spring Vault Configuration
Sample application to demonstrate the working of the Spring vault service

Pre-requisites:

> Download the Hashicorp's vault service binary from url: [Hashicorp vault for Windows](https://releases.hashicorp.com/vault/0.9.6/vault_0.9.6_windows_amd64.zip?_ga=2.60490926.2009439095.1522077481-792402021.1522077481)

> Extract the service and create a configuration file.
```
backend "inmem" {
}
listener "tcp" {
  address = "0.0.0.0:8200"
  tls_disable = 1
}
disable_mlock = true
```
> Open cmd in the extracted location and start the vault server.
`vault server -config vault.conf`

> Open another instance of the cmd and Set the VAULT_ADDR property.
 `set VAULT_ADDR=http://127.0.0.1:8200`
 
> Generate the shared key and token with attributes like number of required shared keys and threshold limit.
`vault operator init -key-shares=5 -key-threshold=2`

> Unseal the vault to start read/write secrets.
`vault operator unseal <key>`

> Provide vault token property to start accessing the vaults.
`set VAULT_TOKEN=<token>`

> Start providing secrets using the following commands.
`vault write secret/my-application username=username password=password`
