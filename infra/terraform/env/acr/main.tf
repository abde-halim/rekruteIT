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

module "container_registry" {
  source = "../../modules/acr"

  acr_name            = var.acr_name
  resource_group_name = module.infrastructure.resource_group_name
  location            = var.location
  sku                 = var.acr_sku
  admin_enabled       = var.acr_admin_enabled
  georeplications     = var.acr_georeplications

  tags = var.tags
}
