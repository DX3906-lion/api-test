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
  UNIQUE KEY uk_workspace_draft_del (workspace_id, draft_name, deleted),
  KEY idx_workspace_status (workspace_id, status)
);

CREATE TABLE IF NOT EXISTS import_file_meta (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  draft_id BIGINT NOT NULL,
  workspace_id BIGINT NOT NULL,
  origin_file_name VARCHAR(255) NOT NULL,
  storage_path VARCHAR(255) NOT NULL,
  import_format VARCHAR(32) NOT NULL,
  file_size BIGINT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY idx_draft_id (draft_id),
  KEY idx_workspace_id (workspace_id)
);

CREATE TABLE IF NOT EXISTS flow_step (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  draft_id BIGINT NOT NULL,
  parent_step_id BIGINT,
  step_type VARCHAR(32) NOT NULL,
  step_name VARCHAR(128) NOT NULL,
  step_order INT NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  deleted TINYINT NOT NULL DEFAULT 0,
  KEY idx_draft_step (draft_id, step_id)
);
