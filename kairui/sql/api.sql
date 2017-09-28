INSERT INTO t_s_api_method (
  name,
  bean_name,
  method_name,
  parmeters_name,
  need_token,
  is_enabled,
  is_update,
  create_time,
  description
) VALUE (
  'discover.circle.reply',
  'circleService',
  'reply',
  'com.ontheroad.api.request.CircleRequest',
  TRUE,
  TRUE,
  FALSE,
  now(),
  '回复'
)