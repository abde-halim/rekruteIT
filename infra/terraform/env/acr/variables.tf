variable "project_name" {
  description = "Project name"
  type        = string
  default     = "rekruite"
}

variable "resource_group_name" {
  description = "Resource group name"
  type        = string
}

variable "location" {
  description = "Azure region"
  type        = string
  default     = "switzerlandnorth"
}

variable "vnet_address_space" {
  description = "VNet address space"
  type        = list(string)
  default     = ["10.0.0.0/16"]
}

variable "subnet_address_prefixes" {
  description = "Subnet address prefixes"
  type        = list(string)
  default     = ["10.0.2.0/24"]
}

variable "acr_name" {
  description = "ACR name"
  type        = string
}

variable "acr_sku" {
  description = "ACR SKU tier"
  type        = string
  default     = "Premium"
}

variable "acr_admin_enabled" {
  description = "Enable ACR admin"
  type        = bool
  default     = false
}

variable "acr_georeplications" {
  description = "ACR georeplications"
  type = list(object({
    location                = string
    zone_redundancy_enabled = bool
    tags                    = map(string)
  }))
  default = [
    {
      location                = "East US"
      zone_redundancy_enabled = true
      tags                    = {}
    },
    {
      location                = "North Europe"
      zone_redundancy_enabled = true
      tags                    = {}
    }
  ]
}

variable "tags" {
  description = "Resource tags"
  type        = map(string)
  default = {
    environment = "production"
    project     = "rekruite"
  }
}
