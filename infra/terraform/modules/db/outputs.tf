output "mysql_server_id" {
  description = "MySQL server ID"
  value       = azurerm_mysql_flexible_server.main.id
}

output "mysql_server_name" {
  description = "MySQL server name"
  value       = azurerm_mysql_flexible_server.main.name
}

output "mysql_fqdn" {
  description = "MySQL server FQDN"
  value       = azurerm_mysql_flexible_server.main.fqdn
}

output "database_id" {
  description = "Database ID"
  value       = azurerm_mysql_flexible_database.main.id
}

output "database_name" {
  description = "Database name"
  value       = azurerm_mysql_flexible_database.main.name
}
