resource "azurerm_mysql_flexible_server" "main" {
  name                   = var.server_name
  resource_group_name    = var.resource_group_name
  location               = var.location
  administrator_login    = var.administrator_login
  administrator_password = var.administrator_password
  sku_name               = var.sku_name

  storage {
    iops    = var.storage_iops
    size_gb = var.storage_size_gb
  }

  backup_retention_days        = var.backup_retention_days
  geo_redundant_backup_enabled = var.geo_redundant_backup_enabled
  # high_availability_enabled        = var.high_availability_enabled
  # zone_redundant_high_availability = var.zone_redundant_high_availability

  tags = var.tags
}

resource "azurerm_mysql_flexible_database" "main" {
  name                = var.database_name
  resource_group_name = var.resource_group_name
  server_name         = azurerm_mysql_flexible_server.main.name
  charset             = var.charset
  collation           = var.collation
}
