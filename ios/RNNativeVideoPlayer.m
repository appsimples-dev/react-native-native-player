
#import "RNNativeVideoPlayer.h"

@implementation RNNativeVideoPlayer

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(openPlayer:(NSString *)url)
{
    AVPlayer *player = [AVPlayer playerWithURL:url];
    // create a player view controller
    AVPlayerViewController *controller = [[AVPlayerViewController alloc] init];
    [self presentViewController:controller animated:YES completion:nil];
    controller.player = player;
    [player play];
}

@end
  