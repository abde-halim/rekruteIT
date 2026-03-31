provider "azurerm" {
  features {}
}

module "infrastructure" {
  source = "../../modules/base"

  project_name            = var.project_name
  resource_group_name     = var.resource_group_name
  location                = var.location
  vnet_address_space      = var.vnet_address_space
  subnet_address_prefixes = var.subnet_address_prefixes

  tags = var.tags
}

module "database" {
  source = "../../modules/db"

  server_name                      = var.db_server_name
  resource_group_name              = module.infrastructure.resource_group_name
  location                         = var.location
  administrator_login              = var.db_admin_login
  administrator_password           = var.db_admin_password
  sku_name                         = var.db_sku_name
  storage_size_gb                  = var.db_storage_size_gb
  backup_retention_days            = var.db_backup_retention_days
  geo_redundant_backup_enabled     = var.db_geo_redundant_backup
  high_availability_enabled        = var.db_high_availability
  zone_redundant_high_availability = var.db_zone_redundant_ha
  database_name                    = var.db_name

  tags = var.tags
}
