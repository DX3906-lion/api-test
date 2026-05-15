CREATE TABLE IF NOT EXISTS system_workspace (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  workspace_code VARCHAR(64) NOT NULL,
  workspace_name VARCHAR(128) NOT NULL,
  description VARCHAR(512),
  owner_user_id BIGINT,
  status VARCHAR(32) NOT NULL DEFAULT 'ACTIVE',
  created_by VARCHAR(64),
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_by VARCHAR(64),
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  deleted TINYINT NOT NULL DEFAULT 0,
  UNIQUE KEY uk_workspace_code (workspace_code)
);

CREATE TABLE IF NOT EXISTS script_draft (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  workspace_id BIGINT NOT NULL,
  draft_name VARCHAR(128) NOT NULL,
  source_type VARCHAR(32) NOT NULL,
  import_format VARCHAR(32),
  status VARCHAR(32) NOT NULL DEFAULT 'DRAFT',
  raw_file_path VARCHAR(255),
  created_by VARCHAR(64),
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_by VARCHAR(64),
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  deleted TINYINT NOT NULL DEFAULT 0,
  KEY idx_workspace_status (workspace_id, status)
);

CREATE TABLE IF NOT EXISTS flow_step (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  draft_id BIGINT NOT NULL,
  parent_step_id BIGINT,
  step_type VARCHAR(32) NOT NULL,
  step_name VARCHAR(128) NOT NULL,
  step_order INT NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  ref_mode VARCHAR(32),
  ref_target_id BIGINT,
  ref_version VARCHAR(32),
  created_by VARCHAR(64),
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_by VARCHAR(64),
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  deleted TINYINT NOT NULL DEFAULT 0,
  KEY idx_draft_order (draft_id, step_order)
);

CREATE TABLE IF NOT EXISTS payload_content (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  draft_id BIGINT NOT NULL,
  step_id BIGINT NOT NULL,
  content_type VARCHAR(32) NOT NULL,
  raw_body LONGTEXT,
  tree_cache LONGTEXT,
  created_by VARCHAR(64),
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_by VARCHAR(64),
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  deleted TINYINT NOT NULL DEFAULT 0,
  KEY idx_draft_step (draft_id, step_id)
);

CREATE TABLE IF NOT EXISTS field_config (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  draft_id BIGINT NOT NULL,
  step_id BIGINT NOT NULL,
  field_path VARCHAR(255) NOT NULL,
  config_type VARCHAR(32) NOT NULL,
  param_mode VARCHAR(32),
  variable_name VARCHAR(64),
  variable_label VARCHAR(64),
  remark VARCHAR(512),
  created_by VARCHAR(64),
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_by VARCHAR(64),
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  deleted TINYINT NOT NULL DEFAULT 0,
  KEY idx_draft_step_field (draft_id, step_id, field_path(128))
);

CREATE TABLE IF NOT EXISTS script_variable (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  draft_id BIGINT NOT NULL,
  step_id BIGINT,
  var_name VARCHAR(64) NOT NULL,
  var_label VARCHAR(64) NOT NULL,
  data_type VARCHAR(32) NOT NULL,
  source_type VARCHAR(32) NOT NULL,
  scope_type VARCHAR(32) NOT NULL,
  source_expression VARCHAR(255),
  created_by VARCHAR(64),
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_by VARCHAR(64),
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  deleted TINYINT NOT NULL DEFAULT 0,
  KEY idx_draft_var (draft_id, var_name)
);

CREATE TABLE IF NOT EXISTS assertion_config (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  draft_id BIGINT NOT NULL,
  step_id BIGINT NOT NULL,
  assert_type VARCHAR(32) NOT NULL,
  expression VARCHAR(255) NOT NULL,
  expected_value VARCHAR(255),
  enabled TINYINT NOT NULL DEFAULT 1,
  created_by VARCHAR(64),
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_by VARCHAR(64),
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  deleted TINYINT NOT NULL DEFAULT 0,
  KEY idx_draft_step (draft_id, step_id)
);

CREATE TABLE IF NOT EXISTS extractor_config (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  draft_id BIGINT NOT NULL,
  step_id BIGINT NOT NULL,
  extract_type VARCHAR(32) NOT NULL,
  expression VARCHAR(255) NOT NULL,
  target_var_name VARCHAR(64) NOT NULL,
  target_var_label VARCHAR(64),
  enabled TINYINT NOT NULL DEFAULT 1,
  created_by VARCHAR(64),
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_by VARCHAR(64),
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  deleted TINYINT NOT NULL DEFAULT 0,
  KEY idx_draft_step (draft_id, step_id)
);

CREATE TABLE IF NOT EXISTS script_version (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  workspace_id BIGINT NOT NULL,
  script_code VARCHAR(64) NOT NULL,
  script_name VARCHAR(128) NOT NULL,
  version_no INT NOT NULL,
  source_draft_id BIGINT NOT NULL,
  status VARCHAR(32) NOT NULL DEFAULT 'ACTIVE',
  created_by VARCHAR(64),
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_by VARCHAR(64),
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  deleted TINYINT NOT NULL DEFAULT 0,
  UNIQUE KEY uk_script_ver (script_code, version_no)
);

CREATE TABLE IF NOT EXISTS case_version (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  workspace_id BIGINT NOT NULL,
  case_code VARCHAR(64) NOT NULL,
  case_name VARCHAR(128) NOT NULL,
  script_version_id BIGINT NOT NULL,
  version_no INT NOT NULL,
  status VARCHAR(32) NOT NULL DEFAULT 'ACTIVE',
  created_by VARCHAR(64),
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_by VARCHAR(64),
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  deleted TINYINT NOT NULL DEFAULT 0,
  UNIQUE KEY uk_case_ver (case_code, version_no)
);
