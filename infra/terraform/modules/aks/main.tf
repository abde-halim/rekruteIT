provider "azurerm" {
  features {}
}

# Resource Group
resource "azurerm_resource_group" "rg" {
  name     = "rg-rekruite-prod"
  location = "spaincentral"
}

# AKS Cluster
resource "azurerm_kubernetes_cluster" "aks" {
  name                = "aks"
  location            = azurerm_resource_group.rg.location
  resource_group_name = azurerm_resource_group.rg.name
  dns_prefix          = "aks-dns"

  kubernetes_version = "1.33.7"

  # Identity
  identity {
    type = "SystemAssigned"
  }

  # Default Node Pool
  default_node_pool {
    name       = "nodepool1"
    node_count = 1
    vm_size    = "Standard_DS2_v2"

    # IMPORTANT: Do NOT set availability_zones
    # availability_zones = ["1"] ❌ (this caused your error)
  }

  # RBAC
  role_based_access_control_enabled = true
  local_account_disabled            = false

  # Networking
  network_profile {
    network_plugin      = "azure"
    network_plugin_mode = "overlay"
    load_balancer_sku   = "standard"
    network_policy      = "none"
  }

  # Auto-upgrade
  automatic_upgrade_channel = "patch"

  # Security / Identity features
  oidc_issuer_enabled       = true
  workload_identity_enabled = true

  # Image cleaner
  image_cleaner_enabled = true

  # Monitoring disabled (as per config)
  oms_agent {
    log_analytics_workspace_id = null
  }

  tags = {
    environment = "prod"
  }
}
